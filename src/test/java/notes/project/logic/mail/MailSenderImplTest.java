package notes.project.logic.mail;

import notes.project.logic.utils.mail.MailTemplateHelper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
class MailSenderImplTest {
    @Mock
    private MailTemplateHelper mailTemplateHelper;
    @Mock
    private JavaMailSender javaMailSender;
}
