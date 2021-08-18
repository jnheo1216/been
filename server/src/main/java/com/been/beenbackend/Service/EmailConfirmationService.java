package com.been.beenbackend.Service;

import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.Clock;

@RequiredArgsConstructor
@Service
public class EmailConfirmationService {
//    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;
    /**
     * 이메일 인증 토큰 생성
     * @return
     */
    public void createEmailConfirmation(int id, String email){
        System.out.println(id);
        System.out.println(email);
        String userId = Integer.toString(id);
        Assert.hasText(userId,"아이디 입력은 필수 입니다.");
        Assert.hasText(email,"수신자 이메일 입력은 필수 입니다.");

//        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(userId);
//        confirmationTokenRepository.save(emailConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Been 회원가입 이메일 인증");
        mailMessage.setText("링크를 눌러 회원가입을 완료하십시오. http://i5b301.p.ssafy.io:8081/user/confirmEmail?id="+userId);
        emailSenderService.sendEmail(mailMessage);

//        return emailConfirmationToken.getId();
    }

    public void createTemporaryPasswordEmail(String email, String password){
        System.out.println(email);
        Assert.hasText(email,"수신자 이메일 입력은 필수 입니다.");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("BEEN 임시 비밀번호");
        mailMessage.setText("임시비밀번호: "+password);
        emailSenderService.sendEmail(mailMessage);

//        return emailConfirmationToken.getId();
    }

//    /**
//     * 유효한 토큰 가져오기
//     * @param confirmationTokenId
//     */
//    public ConfirmationToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId){
//        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(),false);
//        return confirmationToken.orElseThrow(()-> new BadRequestException(ValidationConstant.TOKEN_NOT_FOUND));
//    };

}
