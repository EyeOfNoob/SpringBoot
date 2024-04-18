package com.yedam.app.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Data 내부 기능을 별도로 지정도 가능.
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookVO {
	private int bookNo;
	private String bookName;
	private String bookCoverimg;
	//JSON포맷 yyyy-MM-dd
	//@JsonFormat(pattern="yyyy-MM-dd")
	//기본포맷 yyyy/MM/dd
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bookDate;
	private int bookPrice;
	private String bookPublisher;
	private String bookInfo;
	
	private Integer rentTotalPrice;
	private Integer rentCount;
}
