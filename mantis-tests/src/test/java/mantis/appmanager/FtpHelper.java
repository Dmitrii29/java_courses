package mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Митрич on 01.05.2017.
 */
public class FtpHelper {

  private final ApplicationManager app;
  private FTPClient ftp;

  public FtpHelper(ApplicationManager app) {
    this.app = app;
    ftp = new FTPClient();//выполняется инициализация, созадётся FTP клиент
  }


  // метод загружает новый новый для капчи и временно переименовывает его
  public void upload(File file, String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(backup); //сначала удаляем предыдущую резервную копию
    ftp.rename(target, backup); //переименовываем удаляемый файл и делаем из него резервную копию
    ftp.enterLocalPassiveMode(); //включаетс пассивный режим передачи данных
    ftp.storeFile(target, new FileInputStream(file)); // FileInputStream побайтовое чтение файла, читаются из локального файла и передаются на удалённый реп
    ftp.disconnect();
  }

  //восстанавливаем исходную конфигурацию тестируемой системы
  public void  restore(String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }
}
