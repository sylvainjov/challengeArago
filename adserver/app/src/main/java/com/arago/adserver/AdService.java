package main.java.com.arago.adserver;

public class AdService extends AdServiceGrpc.AdServiceImplBase {

    private static final Jedis redisClient = new Jedis("localhost");

    @Override
    public void createAd(CreateAdRequest request, StreamObserver<CreateAdResponse> responseObserver) {
        String adId = UUID.randomUUID().toString();
        Ad ad = Ad.newBuilder()
                .setId(adId)
                .setTitle(request.getTitle())
                .setDescription(request.getDescription())
                .setUrl(request.getUrl())
                .build();

        // Stocker l'annonce dans Redis
        redisClient.set("ad:" + adId, ad.toString());

        // Envoyer la réponse avec l'ID de l'annonce créée
        CreateAdResponse response = CreateAdResponse.newBuilder()
                .setId(adId)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAdById(GetAdRequest request, StreamObserver<AdResponse> responseObserver) {
        String adData = redisClient.get("ad:" + request.getId());

        if (adData == null) {
            responseObserver.onError(new Exception("Ad not found"));
            return;
        }

        Ad ad = Ad.newBuilder().setId(request.getId()).build();
        AdResponse response = AdResponse.newBuilder().setAd(ad).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void serveAd(ServeAdRequest request, StreamObserver<AdResponse> responseObserver) {
        String adData = redisClient.get("ad:" + request.getId());

        if (adData == null) {
            responseObserver.onError(new Exception("Ad not found"));
            return;
        }

        Ad ad = Ad.newBuilder().setId(request.getId()).build();
        AdResponse response = AdResponse.newBuilder().setAd(ad).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        // Appeler le service de suivi des impressions ici (via gRPC)
    }
}
