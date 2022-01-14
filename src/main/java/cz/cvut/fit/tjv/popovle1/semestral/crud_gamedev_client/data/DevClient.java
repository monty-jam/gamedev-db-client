package cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.data;

import cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.dto.DevDTO;
import cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.ui.DevView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Duration;
import java.util.Collection;

@Component
public class DevClient {
    private final WebClient devWebClient;
    private final DevView devView;
    private Long currentId;

    public DevClient(@Value("${backend_url}") String backedUrl, DevView devView) {
        devWebClient = WebClient.create(backedUrl + "/devs");
        this.devView = devView;
    }

    public Long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Long currentId) {
        this.currentId = currentId;
        if(currentId != null)
            try {
                read();
            } catch (WebClientException e) {
                this.currentId = null;
                throw e;
            }
    }

    public DevDTO create(DevDTO dev) {
        return devWebClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dev)
                .retrieve()
                .bodyToMono(DevDTO.class)
                .block(Duration.ofSeconds(5));
    }

    public Collection<DevDTO> readAll() {
        return devWebClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(DevDTO.class)
                .collectList()
                .block();
    }

    public DevDTO read() {
        if(currentId == null)
            throw new IllegalStateException("Current Id not set.");
        return devWebClient.get()
                .uri("/{id}", currentId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DevDTO.class)
                .block();
    }

    public void update(DevDTO dev) {
        if(currentId == null)
            throw new IllegalStateException("Current id is not set.");
        dev.setId(currentId);
        devWebClient.put()
                .uri("/{id}", currentId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dev)
                .retrieve()
                .toBodilessEntity()
                .subscribe(x -> {}, devView::printErrorUpdate);
    }

    public void delete() {
        if(currentId == null)
            throw new IllegalStateException("Current id is not set.");
        devWebClient.delete()
                .uri("/{id}", currentId)
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        x -> setCurrentId(null),
                        devView::printErrorDelete
                );
    }
}
