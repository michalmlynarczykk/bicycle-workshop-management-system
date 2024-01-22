package com.michalmlynarczyk.workshopmanagementservice.service;

import com.michalmlynarczyk.common.model.dto.broker.workshop.WorkshopJoinApplicationApprovedEvent;
import com.michalmlynarczyk.workshopmanagementservice.client.broker.BrokerClient;
import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopJoinApplicationAlreadyExistsException;
import com.michalmlynarczyk.workshopmanagementservice.exception.WorkshopJoinApplicationNotFoundException;
import com.michalmlynarczyk.workshopmanagementservice.mapper.WorkshopJoinApplicationMapper;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinApplicationRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponseWrapper;
import com.michalmlynarczyk.workshopmanagementservice.model.entity.WorkshopJoinApplication;
import com.michalmlynarczyk.workshopmanagementservice.repository.WorkshopJoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class WorkshopJoinServiceImpl implements WorkshopJoinService {

    private final WorkshopJoinRepository workshopJoinRepository;

    private final WorkshopService workshopService;

    private final BrokerClient brokerClient;


    @Override
    public WorkshopJoinApplicationResponse joinWorkshop(final WorkshopJoinApplicationRequest workshopJoinApplicationRequest,
                                                        final UUID userId) {
        log.debug("joinWorkshop() - enter - workshopJoinApplicationRequest = {} - userId = {}", workshopJoinApplicationRequest, userId);
        checkIfWorkshopExistsAndRequestIsUnique(workshopJoinApplicationRequest, userId);

        final WorkshopJoinApplication savedWorkshopJoinApplication = workshopJoinRepository.save(WorkshopJoinApplication.of(workshopJoinApplicationRequest.workshopId(), userId));
        log.debug("joinWorkshop() - exit - savedWorkshopJoinApplication = {}", savedWorkshopJoinApplication);
        return WorkshopJoinApplicationMapper.toDto(savedWorkshopJoinApplication);
    }


    private void checkIfWorkshopExistsAndRequestIsUnique(final WorkshopJoinApplicationRequest workshopJoinApplicationRequest, final UUID userId) {
        workshopService.getWorkshopOrThrowException(workshopJoinApplicationRequest.workshopId());
        final Optional<WorkshopJoinApplication> optionalWorkshopJoin = workshopJoinRepository
                .findByWorkshopIdAndUserId(workshopJoinApplicationRequest.workshopId(), userId);
        if (optionalWorkshopJoin.isPresent()) {
            throw new WorkshopJoinApplicationAlreadyExistsException("User with userId = {0} already requested joining workshop with workshopId = {1}",
                    userId, workshopJoinApplicationRequest.workshopId());
        }
    }


    @Override
    public WorkshopJoinApplicationResponseWrapper getAllWorkshopJoinApplications(final UUID workshopId) {
        log.trace("getAllWorkshopJoinApplications() - enter - workshopId = {}", workshopId);
        final List<WorkshopJoinApplicationResponse> workshopJoinApplicationRespons = workshopJoinRepository.findAllByWorkshopId(workshopId)
                .stream()
                .map(WorkshopJoinApplicationMapper::toDto)
                .toList();

        log.trace("getAllWorkshopJoinApplications() - exit - workshopJoinApplicationRespons count = {}", workshopJoinApplicationRespons.size());
        return new WorkshopJoinApplicationResponseWrapper(workshopJoinApplicationRespons);
    }


    @Override
    @Transactional
    public void approveOrRejectWorkshopJoinApplication(final String joinRequestId, final boolean approved, final UUID workshopId) {
        log.debug("approveOrRejectWorkshopJoinApplication() - enter - joinRequestId = {} - approved = {} - workshopId = {}",
                joinRequestId, approved, workshopId);
        final WorkshopJoinApplication workshopJoinApplication = getWorkshopJoinApplicationOrThrowException(joinRequestId, workshopId);
        if (approved) {
            workshopJoinApplication.approve();
            brokerClient.notifyAboutWorkshopJoinApplicationApproved(
                    new WorkshopJoinApplicationApprovedEvent(workshopId, workshopJoinApplication.getUserId())
            );
        } else {
            workshopJoinApplication.reject();
        }
        log.debug("approveOrRejectWorkshopJoinApplication() - exit - workshopJoinApplication = {}", workshopJoinApplication);
    }


    private WorkshopJoinApplication getWorkshopJoinApplicationOrThrowException(final String joinRequestId, final UUID workshopId) {
        final Optional<WorkshopJoinApplication> optionalWorkshopJoin = workshopJoinRepository.findByIdAndWorkshopId(joinRequestId, workshopId);
        if (optionalWorkshopJoin.isEmpty()) {
            throw new WorkshopJoinApplicationNotFoundException("Workshop join application with id = {0} and workshopId = {1} not found",
                    joinRequestId, workshopId);
        }
        return optionalWorkshopJoin.get();
    }

}
