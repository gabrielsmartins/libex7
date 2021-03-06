package br.edu.utfpr.libex7.adapters.persistence.adapters.author;

import java.util.List;
import java.util.Optional;

import br.edu.utfpr.libex7.adapters.persistence.entity.authors.AuthorEntity;
import br.edu.utfpr.libex7.adapters.persistence.mapper.authors.AuthorPersistenceMapper;
import br.edu.utfpr.libex7.adapters.persistence.service.authors.AuthorPersistenceService;
import br.edu.utfpr.libex7.application.domain.authors.Author;
import br.edu.utfpr.libex7.application.ports.out.authors.SaveAuthorPort;
import br.edu.utfpr.libex7.application.ports.out.authors.SearchAuthorPort;
import br.edu.utfpr.libex7.application.ports.out.users.RemoveUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorPersistenceAdapter implements SaveAuthorPort, SearchAuthorPort, RemoveUserPort {

    private final AuthorPersistenceService service;
    private final AuthorPersistenceMapper mapper;

    @Override
    public Author save(Author author) {
        AuthorEntity authorEntity = mapper.mapToEntity(author);
        service.save(authorEntity);
        return mapper.mapToDomain(authorEntity);
    }

    @Override
    public Optional<Author> findById(Long id) {
        Optional<AuthorEntity> optionalAuthorEntity = service.findById(id);
        if(optionalAuthorEntity.isPresent()){
            AuthorEntity authorEntity = optionalAuthorEntity.get();
            return Optional.ofNullable(mapper.mapToDomain(authorEntity));
        }
        return Optional.empty();
    }

    @Override
    public List<Author> findByName(String name) {
    	List<AuthorEntity> authors = service.findByName(name);
        return mapper.mapToDomain(authors);
    }

    @Override
    public List<Author> findAll() {
        List<AuthorEntity> authorEntities = service.findAll();
        return mapper.mapToDomain(authorEntities);
    }

    @Override
    public void remove(Long id) {
        service.remove(id);
    }
}
