package com.sam.employee.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sam.employee.model.Employee;

@Repository
public interface EmlployeeElasticRepo  extends ElasticsearchRepository<Employee, Integer>{

}
