package com.sbs.kyudo.middlewaredoc.externalservice.caller;

import com.sbs.kyudo.middlewaredoc.dto.ExecuteKyudoActionRequest;
import com.sbs.kyudo.middlewaredoc.dto.ExecuteKyudoActionResponse;


public interface ExternalSoapApiCaller {
	ExecuteKyudoActionResponse callExternalSoapApi(ExecuteKyudoActionRequest executeKyudoActionRequest);

}
