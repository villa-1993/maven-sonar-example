package exampleJMockit;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class MailControllerTest {

    @Tested
    MailController mailController;

    @Injectable
    private MailService mailService;


    @Test
    public void expection_of_private_method() throws Exception {
        new Expectations() {
            {
                mailService.send("Hello");
                result = "Echo Hello";
            }
        };

        String result = mailController.send("Hello");

        assertEquals("Echo Hello", result);
    }

    @Test
    public void mock_a_static_method() {
        assertEquals("Only abc", MailController.only("abc"));

        new MockUp<MailController>() {
            @Mock
            String only(String msg) {
                return "Besides " + msg;
            }
        };

        assertEquals("Besides abc", MailController.only("abc"));
    }

    //    @Mocked(stubOutClassInitialization = true) final MailUtils unused = null;
    @Mocked MailUtils unused;

    @Test
    public void verify_a_static_method() {

        mailController.displayTwice("xyz");

        new Verifications() {{
            MailUtils.show(anyString);
            times = 2;
        }};

        mailController.displayTwice("abc");

        new Verifications() {{
            MailUtils.show("abc");
            times = 2;
        }};
    }
}