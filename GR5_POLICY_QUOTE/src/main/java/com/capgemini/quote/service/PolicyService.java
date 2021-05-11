package com.capgemini.quote.service;

import java.util.List;

import com.capgemini.quote.bean.BusinessSegment;
import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyDetails;
import com.capgemini.quote.bean.PolicyQuestion;

public interface PolicyService {
	
	public List<PolicyQuestion> getPolicyQuestionBySegId(String busSegId);

	public String setPolicyDetails(PolicyDetails polDetails);
	
    public List<BusinessSegment> getBsList();
    
    public Policy createPolicy(Policy p);
    
    public int calculatePremium(long policyNo);
    
    public List<Policy> getPolicyByAccNo(long accountNo);

}
