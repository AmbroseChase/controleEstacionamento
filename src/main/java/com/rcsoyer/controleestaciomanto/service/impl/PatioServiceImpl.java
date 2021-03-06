package com.rcsoyer.controleestaciomanto.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rcsoyer.controleestaciomanto.domain.Estacionamento;
import com.rcsoyer.controleestaciomanto.domain.Patio;
import com.rcsoyer.controleestaciomanto.repository.EstacionamentoRepository;
import com.rcsoyer.controleestaciomanto.repository.PatioRepository;
import com.rcsoyer.controleestaciomanto.service.PatioService;
import com.rcsoyer.controleestaciomanto.service.dto.PatioDTO;
import com.rcsoyer.controleestaciomanto.service.mapper.PatioMapper;


/**
 * Service Implementation for managing Patio.
 */
@Service
@Transactional
public class PatioServiceImpl implements PatioService {

  private final PatioMapper patioMapper;

  private final PatioRepository patioRepository;

  private final EstacionamentoRepository estacionamentoRepository;

  private final Logger log = LoggerFactory.getLogger(PatioServiceImpl.class);

  public PatioServiceImpl(PatioRepository patioRepository, PatioMapper patioMapper,
      EstacionamentoRepository estacionamentoRepository) {
    this.patioMapper = patioMapper;
    this.patioRepository = patioRepository;
    this.estacionamentoRepository = estacionamentoRepository;
  }

  /**
   * Save a patio.
   *
   * @param patioDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public PatioDTO save(PatioDTO patioDTO) {
    log.debug("Request to save Patio : {}", patioDTO);
    Patio patio = patioMapper.toEntity(patioDTO);
    patio = patioRepository.save(patio);
    return patioMapper.toDto(patio);
  }

  /**
   * Get all the patios.
   *
   * @param pageable the pagination information
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public Page<PatioDTO> findAll(Pageable pageable) {
    log.debug("Request to get all Patios");
    Page<PatioDTO> dtos = patioRepository.findAll(pageable).map(patioMapper::toDto);
    return dtos;
  }

  private void definirVagasDisponiveis(List<PatioDTO> dtos) {
    List<Long> idsPatios = dtos.stream().map(PatioDTO::getId).collect(Collectors.toList());
    List<Estacionamento> vagasDisponiveis = estacionamentoRepository.getVagasDisponiveis(idsPatios);
  }

  /**
   * Get one patio by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public PatioDTO findOne(Long id) {
    log.debug("Request to get Patio : {}", id);
    Patio patio = patioRepository.findOne(id);
    return patioMapper.toDto(patio);
  }

  /**
   * Delete the patio by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Patio : {}", id);
    patioRepository.delete(id);
  }
}
