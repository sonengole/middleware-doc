package com.sbs.kyudo.middlewaredoc.externalservice.caller;

import com.sbs.kyudo.middlewaredoc.dto.KyudoDocRequest;
import com.sbs.kyudo.middlewaredoc.dto.KyudoDocResponse;

public interface ExternalRestApiCaller {
	KyudoDocResponse callExternalRestApi(KyudoDocRequest kyudoDocRequest);

}
