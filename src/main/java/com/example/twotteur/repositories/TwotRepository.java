package com.example.twotteur.repositories;

import com.example.twotteur.models.TwotModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwotRepository extends JpaRepository<TwotModel, Integer>  {
}
