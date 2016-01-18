package exampleJMockit;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
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
    public void testSend() throws Exception {
        new Expectations() { {
            mailService.send("Hello");
            result = "Echo Hello";
        } };

        String result = mailController.send("Hello");

        assertEquals("Echo Hello", result);
    }
}