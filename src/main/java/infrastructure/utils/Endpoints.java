package infrastructure.utils;

public enum Endpoints {
    UsersEndpoint("users");

    String endpointName;


    Endpoints(String endpointName) {
        this.endpointName =endpointName;
    }

    public String getName() {
        return endpointName;
    }
}
