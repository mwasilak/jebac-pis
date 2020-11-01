package pl.sixpinetrees.tournament.service;

import pl.sixpinetrees.tournament.domain.dto.ResultRegistrationForm;

public interface ResultRegistrationService {

    Long registerResults(Long matchId, ResultRegistrationForm resultRegistrationForm);

}
