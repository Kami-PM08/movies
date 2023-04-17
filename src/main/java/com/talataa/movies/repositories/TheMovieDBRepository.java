package com.talataa.movies.repositories;

import com.talataa.movies.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Service
@Log4j2
public class TheMovieDBRepository {

    @Value("${movies.moviesDB.url}")
    private String URL;

    @Value("${movies.moviesDB.apiKey}")
    private String API_KEY;

    public Token generateAuthToken() {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("authentication/token/new")
                .concat("?api_key=").concat(API_KEY);
        Token response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(Token.class);
        client.close();
        return response;
    }

    public Session createSession(String token) throws NotAuthorizedException {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("authentication/session/new")
                .concat("?api_key=").concat(API_KEY);
        String jsonBody = "{\"request_token\": \"".concat(token).concat("\"}");
        Response response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonBody, MediaType.APPLICATION_JSON));
        Session session = response.readEntity(Session.class);
        client.close();
        if (response.getStatus() == 401) throw new NotAuthorizedException(response.getStatusInfo().getReasonPhrase());
        return session;
    }

    public void deleteSession(String sessionId) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("authentication/session")
                .concat("?api_key=").concat(API_KEY)
                .concat("&session_id=").concat(sessionId);
        Response response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        client.close();
        if (response.getStatus() == 404) throw new NotFoundException(response.getStatusInfo().getReasonPhrase());
    }

    public Movie findMovieById(Integer id) {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("movie/").concat(id.toString())
                .concat("?api_key=").concat(API_KEY);
        Movie response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(Movie.class);
        client.close();
        return response;
    }

    public MoviesPage findMoviesByPage(Integer page) {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("discover/movie")
                .concat("?api_key=").concat(API_KEY)
                .concat("&page=").concat(page.toString());
        MoviesPage response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(MoviesPage.class);
        client.close();
        return response;
    }

    public Integer createList(String sessionId, CreateMoviesListProps props) throws NotAuthorizedException {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("list")
                .concat("?api_key=").concat(API_KEY)
                .concat("&session_id=").concat(sessionId);
        Response response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(props, MediaType.APPLICATION_JSON));
        Map<String, Object> responseData = response.readEntity(new GenericType<Map<String, Object>>() {
        });
        client.close();
        if (response.getStatus() == 401) throw new NotAuthorizedException(response.getStatusInfo().getReasonPhrase());
        return (Integer) responseData.get("list_id");
    }

    public MoviesList getList(Integer listId) {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("list/").concat(listId.toString())
                .concat("?api_key=").concat(API_KEY);
        MoviesList response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(MoviesList.class);
        client.close();
        return response;
    }

    public void addMovieToList(String sessionId, Integer listId, Integer movieId) throws NotAuthorizedException, NotFoundException {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("list/").concat(listId.toString())
                .concat("/add_item")
                .concat("?api_key=").concat(API_KEY)
                .concat("&session_id=").concat(sessionId);
        String jsonBody = "{\"media_id\": ".concat(movieId.toString()).concat("}");
        Response response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonBody, MediaType.APPLICATION_JSON));
        client.close();
        if (response.getStatus() == 401) throw new NotAuthorizedException(response.getStatusInfo().getReasonPhrase());
        if (response.getStatus() == 404) throw new NotFoundException(response.getStatusInfo().getReasonPhrase());
    }

    public void removeMovieToList(String sessionId, Integer listId, Integer movieId) throws NotAuthorizedException, NotFoundException {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("list/").concat(listId.toString())
                .concat("/remove_item")
                .concat("?api_key=").concat(API_KEY)
                .concat("&session_id=").concat(sessionId);
        String jsonBody = "{\"media_id\": ".concat(movieId.toString()).concat("}");
        Response response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jsonBody, MediaType.APPLICATION_JSON));
        client.close();
        if (response.getStatus() == 401) throw new NotAuthorizedException(response.getStatusInfo().getReasonPhrase());
        if (response.getStatus() == 404) throw new NotFoundException(response.getStatusInfo().getReasonPhrase());
    }

    public void clearList(String sessionId, Integer listId) throws NotAuthorizedException, NotFoundException {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("list/").concat(listId.toString())
                .concat("/clear")
                .concat("?api_key=").concat(API_KEY)
                .concat("&session_id=").concat(sessionId)
                .concat("&confirm=").concat("true");
        Response response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .method("POST");
        client.close();
        if (response.getStatus() == 401) throw new NotAuthorizedException(response.getStatusInfo().getReasonPhrase());
        if (response.getStatus() == 404) throw new NotFoundException(response.getStatusInfo().getReasonPhrase());
    }

    public void deleteList(String sessionId, Integer listId) throws NotAuthorizedException, NotFoundException {
        Client client = ClientBuilder.newClient();
        String apiUrl = URL.concat("list/").concat(listId.toString())
                .concat("?api_key=").concat(API_KEY)
                .concat("&session_id=").concat(sessionId);
        Response response = client.target(apiUrl)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        client.close();
        if (response.getStatus() == 401) throw new NotAuthorizedException(response.getStatusInfo().getReasonPhrase());
        if (response.getStatus() == 404) throw new NotFoundException(response.getStatusInfo().getReasonPhrase());
    }

}
