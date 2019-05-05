package com.raddarapp.data.general.datasource.base.contract.preferences.base;

import com.google.common.collect.Sets;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.domain.model.enums.UserStatusType;

import java.util.Set;

public abstract class DefaultValuesPrefererences {

    private static final String NOTIFICATION_TOPIC_COMMENTS = "new_comment";
    private static final String NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS = "favourite_flag";
    private static final String NOTIFICATION_TOPIC_FOOTPRINTS_DEAD = "flag_dead";
    private static final String NOTIFICATION_TOPIC_FOOTPRINT_DEAD_WARNING = "flag_dead_warning";
    private static final String NOTIFICATION_TOPIC_COMPLIMENTS = "compliments";

    protected static final int DEFAULT_VALUE_INT = 0;
    protected static final long DEFAULT_VALUE_LONG = 0L;
    protected static final float DEFAULT_VALUE_FLOAT = 0.0F;
    protected static final String DEFAULT_VALUE_STRING = "";
    protected static final int DEFAULT_VALUE_INT_NEGATIVE = -1;
    protected static final int DEFAULT_VALUE_INT_TWO = 2;
    protected static final int DEFAULT_VALUE_INT_ROLE = UserRoleType.USER.getValue();
    protected static final int DEFAULT_VALUE_INT_STATUS = UserStatusType.ACTIVE.getValue();
    protected static final String DEFAULT_VALUE_STRING_BIRTHDATE = "2000-01-01T00:00:00.000Z";
    protected static final Set<String> DEFAULT_NOTIFICATION_TOPICS =
            Sets.newHashSet(NOTIFICATION_TOPIC_COMMENTS, NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS,
                    NOTIFICATION_TOPIC_FOOTPRINTS_DEAD, NOTIFICATION_TOPIC_FOOTPRINT_DEAD_WARNING, NOTIFICATION_TOPIC_COMPLIMENTS);
}
