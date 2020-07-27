package my.pkg.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import my.pkg.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.SkipException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;


public class TestBase {


    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
      //  try {
            app.init();
            app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");

        }
        public boolean isIssueOpen ( int issueId) throws RemoteException, ServiceException, MalformedURLException {
            IssueData issue = app.soap().getIssue(issueId);
            if (issue.getStatus().getName().equals("resolved") || issue.getStatus().getName().equals("closed")) {
                return false;
            }
            return true;
        }

        public void skipIfNotFixed ( int issueId) throws RemoteException, ServiceException, MalformedURLException {
            if (isIssueOpen(issueId)) {
                throw new SkipException("Ignored because of issue " + issueId);
            }
        }

        @AfterSuite(alwaysRun = true)
        public void tearDown () throws IOException {
            app.ftp().restore("config_inc.php.bak", "config_inc.php");
            app.stop();

        }
    }
