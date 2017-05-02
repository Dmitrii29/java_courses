package mantis.appmanager;

import mantis.model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Митрич on 02.05.2017.
 */
public class MailHelper {

  private ApplicationManager app;
  private final Wiser wiser;

  public MailHelper(ApplicationManager app) {
    this.app = app;
    wiser = new Wiser(); // почтовый сервер
  }

  //так как почта может прийти не сразу, то метод отвечает за ожидание
  //кол-во ожидаемый писем и время
  public List<MailMessage> waitForMail(int count, long timeout) throws MessagingException, IOException {
    long start = System.currentTimeMillis(); //запоминаем текущее время

    //цикл выполняется пока время старта < чем время старта+таймаут
    while (System.currentTimeMillis() < start + timeout) {
      //если почты пришло нужное кол-во, то ожидание можно прекращать
      if (wiser.getMessages().size() >= count) {
        return wiser.getMessages().stream()
                .map((m) -> toModelMail(m)).collect(Collectors.toList());
      }
      //если почты всё еще мало то ждём 1000мс
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail");
  }


  //преобразоание реальных почтовых сообщений в модельные
  public static MailMessage toModelMail(WiserMessage m) {
    try {
      MimeMessage mm = m.getMimeMessage();
      return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
    } catch (MessagingException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void start() {
    wiser.start();
  }

  public void stop() {
    wiser.stop();
  }
}
