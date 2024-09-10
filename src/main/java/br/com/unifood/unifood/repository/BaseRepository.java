package br.com.unifood.unifood.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseRepository<T, ID> {
    Page<T> search(String search, Pageable pageable);

    List<String> getFieldsSearchable();
}
