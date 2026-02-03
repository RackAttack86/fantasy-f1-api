package com.fantasyf1.fantasy_f1_api.team.service;

import com.fantasyf1.fantasy_f1_api.common.exception.DuplicateResourceException;
import com.fantasyf1.fantasy_f1_api.common.exception.ResourceNotFoundException;
import com.fantasyf1.fantasy_f1_api.team.dto.CreateTeamRequest;
import com.fantasyf1.fantasy_f1_api.team.dto.TeamResponse;
import com.fantasyf1.fantasy_f1_api.team.dto.UpdateTeamRequest;
import com.fantasyf1.fantasy_f1_api.team.entity.Team;
import com.fantasyf1.fantasy_f1_api.team.mapper.TeamMapper;
import com.fantasyf1.fantasy_f1_api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Constructor injection
public class TeamService {
    
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Transactional
    public TeamResponse createTeam(CreateTeamRequest request) {
        if (teamRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Team", "name", request.getName());
        }

        Team team = Team.builder()
                .name(request.getName())
                .fullName(request.getFullName())
                .base(request.getBase())
                .teamPrincipal(request.getTeamPrincipal())
                .build();

        Team savedTeam = teamRepository.save(team);
        return teamMapper.toResponse(savedTeam);
    }

    @Transactional(readOnly = true) // readonly for read operations is for optimization
    public TeamResponse getTeamById(UUID id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id.toString()));
        return teamMapper.toResponse(team);
    }

    @Transactional(readOnly = true)
    public TeamResponse getTeamByName(String name) {
        Team team = teamRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "name", name));
        return teamMapper.toResponse(team);
    }

    @Transactional(readOnly = true)
    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toResponse)
                .toList();
    }

    @Transactional
    public TeamResponse updateTeam(UUID id, UpdateTeamRequest request) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id.toString()));
        
        // Check for name uniqueness if name is being changed
        if (request.getName() != null && !request.getName().equals(team.getName())) {
            if (teamRepository.existsByName(request.getName())) {
                throw new DuplicateResourceException("Team", "name", request.getName());
            }
            team.setName(request.getName());
        }

        if (request.getFullName() != null) {
            team.setFullName(request.getFullName());
        }

        if (request.getBase() != null) {
            team.setBase(request.getBase());
        }

        if (request.getTeamPrincipal() != null) {
            team.setTeamPrincipal(request.getTeamPrincipal());
        }

        Team updatedTeam = teamRepository.save(team);
        return teamMapper.toResponse(updatedTeam);
    }

    @Transactional
    public void deleteTeam(UUID id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team", "id", id.toString());
        }
        teamRepository.deleteById(id);
    }
}
