package com.sbs.kyudo.middlewaredoc.token;
import java.util.Date;
public enum CrelanKeycloakTokenManagerKyudo {
	INSTANCE;
	private volatile CrelanKeycloakToken crelanKeycloakToken;
	private CrelanKeycloakTokenReteieverKyudo crelanKeycloakTokenReteieverKyudo = new CrelanKeycloakTokenReteieverKyudo();
    private volatile Date tokenExpirationDate = new Date();
    private volatile Date lastCheckDate = new Date();
    //private int actualRefreshThreshold;
    public static final int REFRESH_THRESHOLD = 2000;
	//public static final int REFRESH_THRESHOLD = Integer.parseInt(KyudoProperties.get("ATSrefreshTokenThreshold", "2000"));
	
	private CrelanKeycloakTokenManagerKyudo(){
		crelanKeycloakToken = null;
	}
	
	public static CrelanKeycloakTokenManagerKyudo getInstance(){
		return INSTANCE;
	}
	
	public CrelanKeycloakToken getAccessToken() {
		System.out.println("CrelanKeycloakTokenManagerKyudo ---> in getaccesstoken");
		if (tokenRequiresRefresh()) {
			retrieveNewAccessTeken();
		}
		
		return crelanKeycloakToken;
	}
	
	
	public void refresh() {
		crelanKeycloakToken = null;
	}
	
	private synchronized void retrieveNewAccessTeken() {
		if (!tokenRequiresRefresh()) {
			return;
		}
		
		CrelanKeycloakToken newlyRetrievedToken = new CrelanKeycloakToken();
		try {
			this.lastCheckDate = new Date();
			newlyRetrievedToken = crelanKeycloakTokenReteieverKyudo.retrieveNewAccessToken();
			//System.out.println("CrelanKeycloakTokenManagerKyudo ---> keycloaktoken is" +  newlyRetrievedToken);
			if (newlyRetrievedToken.getExpiresIn() != null){
				try{
					int expiry = Integer.parseInt(newlyRetrievedToken.getExpiresIn());
					this.crelanKeycloakToken = newlyRetrievedToken;
					this.tokenExpirationDate = new Date(this.lastCheckDate.getTime() + expiry * 1000);
					//this.calculateActualRefreshThreshold(expiry);
					
				} catch (Exception e) {
					System.out.println("CrelanKeycloakTokenManagerKyudo ---> unable to parse token expiration time" +  e.toString());
					e.printStackTrace();
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("CrelanKeycloakTokenManagerKyudo ---> unable to load token from endpoint" +  e.toString());
		} finally {
			
		}
	}

    boolean tokenRequiresRefresh() {
        if (crelanKeycloakToken == null) {
            return true;
        }

        if (isWithinRefreshThreshold()) {
            return true;
        }
        return false;
    }
    
    private boolean isWithinRefreshThreshold() {
        return (tokenExpirationDate.getTime() - System.currentTimeMillis()) <= CrelanKeycloakTokenManagerKyudo.REFRESH_THRESHOLD;
    }




}
