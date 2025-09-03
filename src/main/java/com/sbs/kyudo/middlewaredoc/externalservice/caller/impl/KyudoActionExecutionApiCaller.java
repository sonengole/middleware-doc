package com.sbs.kyudo.middlewaredoc.externalservice.caller.impl;

import org.springframework.stereotype.Component;

import com.sbs.kyudo.middlewaredoc.dto.ExecuteKyudoActionRequest;
import com.sbs.kyudo.middlewaredoc.dto.ExecuteKyudoActionResponse;
import com.sbs.kyudo.middlewaredoc.externalservice.caller.ExternalSoapApiCaller;

@Component
public class KyudoActionExecutionApiCaller implements ExternalSoapApiCaller{

	@Override
	public ExecuteKyudoActionResponse callExternalSoapApi(ExecuteKyudoActionRequest executeKyudoActionRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
