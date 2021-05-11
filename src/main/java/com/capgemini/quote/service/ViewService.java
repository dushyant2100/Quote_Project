package com.capgemini.quote.service;

import java.util.List;

import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyDetails;
import com.capgemini.quote.bean.PolicyQuestion;
import com.capgemini.quote.customexp.CustomException;

public interface ViewService {
	
	public List<Policy> getPolicybyAccNo(long accountNo);

	public Policy getPolicyByPolicyNo(long policyNo) throws CustomException;

	public List<PolicyDetails> getPolicyDetailsByPolNo(long policyNo);

	public PolicyQuestion getPolQuesByQId(String qId);

}
