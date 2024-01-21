package com.michalmlynarczyk.workshopmanagementservice.service;

import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopJoinRequestAlreadyExistsException;
import com.michalmlynarczyk.workshopmanagementservice.mapper.WorkshopJoinRequestMapper;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponseWrapper;
import com.michalmlynarczyk.workshopmanagementservice.model.entity.WorkshopJoin;
import com.michalmlynarczyk.workshopmanagementservice.repository.WorkshopJoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class WorkshopJoinServiceImpl implements WorkshopJoinService {

    private final WorkshopJoinRepository workshopJoinRepository;

    private final WorkshopService workshopService;


    @Override
    public WorkshopJoinResponse joinWorkshop(final WorkshopJoinRequest workshopJoinRequest,
                                             final UUID userId) {
        log.debug("joinWorkshop() - enter - workshopJoinRequest = {} - userId = {}", workshopJoinRequest, userId);
        checkIfWorkshopExistsAndRequestIsUnique(workshopJoinRequest, userId);

        final WorkshopJoin savedWorkshopJoin = workshopJoinRepository.save(WorkshopJoin.of(workshopJoinRequest.workshopId(), userId));
        log.debug("joinWorkshop() - exit - savedWorkshopJoin = {}", savedWorkshopJoin);
        return WorkshopJoinRequestMapper.toDto(savedWorkshopJoin);
    }


    @Override
    public WorkshopJoinResponseWrapper getAllWorkshopJoinRequests(final UUID workshopId) {
        log.trace("getAllWorkshopJoinRequests() - enter - workshopId = {}", workshopId);
        final List<WorkshopJoinResponse> workshopJoinResponses = workshopJoinRepository.findAllByWorkshopId(workshopId)
                .stream()
                .map(WorkshopJoinRequestMapper::toDto)
                .toList();

        log.trace("getAllWorkshopJoinRequests() - exit - workshopJoinResponses count = {}", workshopJoinResponses.size());
        return new WorkshopJoinResponseWrapper(workshopJoinResponses);
    }


    private void checkIfWorkshopExistsAndRequestIsUnique(final WorkshopJoinRequest workshopJoinRequest, final UUID userId) {
        workshopService.getWorkshopOrThrowException(workshopJoinRequest.workshopId());
        final Optional<WorkshopJoin> optionalWorkshopJoin = workshopJoinRepository
                .findByWorkshopIdAndUserId(workshopJoinRequest.workshopId(), userId);
        if (optionalWorkshopJoin.isPresent()) {
            throw new WorkshopJoinRequestAlreadyExistsException("User with userId = {0} already requested joining workshop with workshopId = {1}",
                    userId, workshopJoinRequest.workshopId());
        }
    }
}
