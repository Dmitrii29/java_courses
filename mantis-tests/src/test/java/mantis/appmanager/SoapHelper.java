package mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import mantis.model.Issue;
import mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.getProperty;

/**
 * Created by Mitrich on 09.05.2017.
 */
public class SoapHelper {
  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible
            (app.getProperty("mantis.soap.username"), app.getProperty("mantis.soap.password"));
/*    преобразуем полученные данные в модельные объекты
    обращаем в поток, ко всем элементам потока применяем функцию map, которая будет из объектов ProjectData
    строить новый объект типа Project с идентификатором, который равен p.getId() и именем p.getName()
    получившийся поток из новых объектов типа Project собираем и возвращаем получившееся множество*/
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL(app.getProperty("mantis.soap.Url")));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories(app.getProperty("mantis.soap.username"), app.getProperty("mantis.soap.password"), BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(app.getProperty("mantis.soap.username"), app.getProperty("mantis.soap.password"), issueData);
    IssueData createdIssueData = mc.mc_issue_get(app.getProperty("mantis.soap.username"), app.getProperty("mantis.soap.password"), issueId);
    return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                      .withName(createdIssueData.getProject().getName()));
  }

  public IssueData getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    return getMantisConnect().mc_issue_get(app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
  }
}
