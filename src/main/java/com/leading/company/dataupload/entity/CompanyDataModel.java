package com.leading.company.dataupload.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
public class CompanyDataModel {

    private int memberId;

    private int loanAmount;

    private int fundedAmntInv;

    private String term;

    private Double intRate;

    private Double installment;

    private String grade;

    private String empTitle;

    private String empLength;

    private String homeOwnership;

    private int annualInc;

    private String verificationStatus;

    private String issueDate;

    private String loanStatus;

    private String descn;

    private String purpose;

    private String title;

    private String addrState;

    private String lastPymtDate;

}
