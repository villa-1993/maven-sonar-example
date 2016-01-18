package exampleJMockit;

/**
 * Created by jacky on 16/1/18.
 */
public class MailController {
    private MailService mailService;

    public
    String send(String content) throws Exception {
        String result = mailService.send(content);
        return result;
    }

}
