package in.ashokit;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	//logic to generate token using jwt
	public static String generateToken(String subject,String secretKey) {
		//token contains header,payload and signature part
		//header part contains jwt information
	return	Jwts.builder()
			//payload contains following details from Id to setExpiration
		    .setId("tk9931")
		    .setSubject(subject)
		    .setIssuer("Ashok IT")
		    .setAudience("ABC_IT")
		    .setIssuedAt(new Date(System.currentTimeMillis()) )
		    //token gets one hour time to get expired
            .setExpiration(new Date(System.currentTimeMillis() +TimeUnit.HOURS.toMillis(1))) //expirt time=(current time + 1 hour)
            //  signature contains
            .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secretKey.getBytes()))
            .compact(); //gives json webtoken (going to return string value)

	}
	public static void main(String[] args) {
		String secretKey="mysecret@1";
		String subject="mytoken";
		
		//generating the token
		String token = JWTUtil.generateToken(subject, secretKey);
		//printing the token
		System.out.println(token);
	
	//code to parse the token (going to return claims body obj)
		JwtParser parser = Jwts.parser();
		Claims claims = parser.setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
		      .parseClaimsJws(token)
		      .getBody();
		
		
		//printing token information
		System.out.println("Token Id ::" +claims.getId());
		System.out.println("Token Issued By :: " +claims.getIssuer());
		System.out.println("Token generated :" +claims.getIssuedAt());
		System.out.println("Token Expiry :" +claims.getExpiration());//token expiry  time will be 1 hour
		
	}
	
	
	/* output
	 * eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJ0azk5MzEiLCJzdWIiOiJteXRva2VuIiwiaXNzIjoiQXNob2sgSVQiLCJhdWQiOiJBQkNfSVQiLCJpYXQiOjE2MzEwMDY2NzMsImV4cCI6MTYzMTAxMDI3M30.agH34UwEbKDEcqT4Sxa08uZ5p8mvqRE6V35AIk0RU6p50X6C6awYjpCYVdcZqwia8rqwIfc5-rn0JLbOEJ4njw
Token Id ::tk9931
Token Issued By :: Ashok IT
Token generated :Tue Sep 07 14:54:33 IST 2021
Token Expiry :Tue Sep 07 15:54:33 IST 2021

	 */
	}