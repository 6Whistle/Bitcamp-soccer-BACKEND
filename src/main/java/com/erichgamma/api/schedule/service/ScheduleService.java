package com.erichgamma.api.schedule.service;

import com.erichgamma.api.common.command.CommandService;
import com.erichgamma.api.common.query.QueryService;
import com.erichgamma.api.schedule.model.Schedule;
import com.erichgamma.api.schedule.model.ScheduleDto;




public interface ScheduleService extends CommandService<ScheduleDto>, QueryService<ScheduleDto> {



}
