package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.FootprintsMainRepository;

import javax.inject.Inject;

public class AddVote extends RosieUseCase {

    public static final String USE_CASE_ADD_VOTE = "addVote";

    private final FootprintsMainRepository footprintMainRepository;

    @Inject
    public AddVote(FootprintsMainRepository footprintMainRepository) {
        this.footprintMainRepository = footprintMainRepository ;
    }

    @UseCase(name = USE_CASE_ADD_VOTE)
    public void addVote(String footprintKey, int addVoteType) throws Exception {
        double actualScope = footprintMainRepository.addVote(footprintKey, addVoteType);
        notifySuccess(actualScope);
    }
}