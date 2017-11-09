package com.spotahome.infrastructure;

import com.spotahome.client.IRestClient;
import com.spotahome.client.RestClient;
import com.spotahome.dal.DummyDal;
import com.spotahome.dal.IDummyDal;
import dagger.Module;
import dagger.Provides;

@Module (
        injects = {
                IDummyDal.class,
                IRestClient.class
        },
        library = true
)
public class InjectionModule {
    @Provides
    IDummyDal provideIDummyApiService(IRestClient client) {
        return new DummyDal(client);
    }

    @Provides
    IRestClient provideIRestClient() {
        return new RestClient();
    }
}
