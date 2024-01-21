package com.michalmlynarczyk.workshopmanagementservice.service;


import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopCreatedEvent;
import com.michalmlynarczyk.workshopmanagementservice.client.broker.BrokerClient;
import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopAlreadyExistsException;
import com.michalmlynarczyk.workshopmanagementservice.mapper.WorkshopMapper;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.CreateWorkshopRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.entity.Workshop;
import com.michalmlynarczyk.workshopmanagementservice.repository.WorkshopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;

    private final BrokerClient brokerClient;


    @Override
    @Transactional
    public WorkshopResponse createWorkshop(final CreateWorkshopRequest createWorkshopRequest,
                                           final CustomAuthenticationPrincipal principal) {
        log.debug("createWorkshop() - enter - createWorkshopRequest = {}", createWorkshopRequest);
        checkIfUserAlreadyHasWorkshop(principal);

        final Workshop workshop = WorkshopMapper.toEntity(createWorkshopRequest, principal.userId());

        final Workshop savedWorkshop = workshopRepository.save(workshop);

        brokerClient.notifyAboutWorkshopCreated(new WorkshopCreatedEvent(savedWorkshop.getId(), principal.userId()));

        log.debug("createWorkshop() - exit - savedWorkshop = {}", savedWorkshop);
        return WorkshopMapper.toDto(savedWorkshop);
    }


    private void checkIfUserAlreadyHasWorkshop(final CustomAuthenticationPrincipal principal) {
        log.debug("checkIfUserAlreadyHasWorkshop() - enter - principal = {}", principal);

        if (principal.workshopId() != null || workshopRepository.findByOwnerId(principal.userId()).isPresent()) {
            throw new WorkshopAlreadyExistsException("Workshop for owner with userId = {0} already exists", principal.userId());
        }

    }
}
