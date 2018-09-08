package app.mvp.retrofit;

import app.mvp.service.*;

public class Config {
    private static final String API_LOCAL = "http://10.0.0.3:8000/api/v1/";
    /*private static final String API_IBGE = "https://servicodados.ibge.gov.br/api/v1/";

    public static RegionService getRegionService() {
        return RetrofitClient.getClient(API_IBGE).create(RegionService.class);
    }*/

    public static LoginService getLoginService() {
        return RetrofitClient.getClient(API_LOCAL).create(LoginService.class);
    }

    public static UserService getUserService() {
        return RetrofitClient.getClient(API_LOCAL).create(UserService.class);
    }

    public static EstablishmentService getEstablishmentService() {
        return RetrofitClient.getClient(API_LOCAL).create(EstablishmentService.class);
    }
}

