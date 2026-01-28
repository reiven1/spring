package com.bs.spring.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demo {
	private int devNo;
	@NotEmpty(message = "이름은 반드시 작성하세요")
	@Pattern(regexp = "[가-힣]{2,}", message = "두글자 이상 한글로 작성하세요")
	private String devName;
	@Min(value = 10, message = "10살 이상만 등록 가능")
	private int devAge;
	@Email(message = "이메일 형식에 맞지 않습니다")
	private String devEmail;
	private String devGender;
	private String[] devLang;
	@Past(message = "미래에서 왔니?")
	private Date birthDay;
	private Address address;
}
