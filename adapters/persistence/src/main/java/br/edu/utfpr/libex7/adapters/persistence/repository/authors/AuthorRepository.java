package br.edu.utfpr.libex7.adapters.persistence.repository.authors;

import br.edu.utfpr.libex7.adapters.persistence.entity.authors.AuthorEntity;
import br.edu.utfpr.libex7.adapters.persistence.repository.GenericRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuthorRepository extends GenericRepository<AuthorEntity, Long> {


    public AuthorRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected AuthorEntity parse(ResultSet resultSet) {
        try{
            AuthorEntity authorEntity = new AuthorEntity();
            Long authorId = resultSet.getLong("CODIGO_AUTOR");
            String authorName = resultSet.getString("NOME_AUTOR");
            authorEntity.setId(authorId);
            authorEntity.setName(authorName);
            return authorEntity;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar informacoes de autor",e);
        }
    }

    @Override
    protected Map<String, Object> getColumnMap(AuthorEntity authorEntity) {
        Map<String, Object> columMap = new LinkedHashMap<>();
        columMap.put("NOME_AUTOR", authorEntity.getName());
        return columMap;
    }




}
