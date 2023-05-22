package com.example.busmanagement.service;

import com.example.busmanagement.entity.Route;
import com.example.busmanagement.entity.Assignment;
import com.example.busmanagement.entity.Driver;
import com.example.busmanagement.model.RouteModel;
import com.example.busmanagement.model.AssignmentModel;
import com.example.busmanagement.model.DriverModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AssignmentService {
    RouteService routeService;
    DriverService driverService;
    private static int AUTO_ID = 1;

    List<Assignment> assignments = new ArrayList<>();

    public List<AssignmentModel> getAllAssignment() {
        List<AssignmentModel> result = new ArrayList<>();
        assignments.forEach(assignment -> {
            AssignmentModel assignmentModel = AssignmentModel
                    .builder()
                    .id(assignment.getId())
                    .tenLaiXe(assignment.getDriver().getName())
                    .idLaiXe(assignment.getDriver().getId())
                    .soLuotChay(assignment.getSoLuotChay())
                    .idTuyen(assignment.getRoute().getId())
                    .build();

            result.add(assignmentModel);
        });
        return result;
    }

    public void creatAssignment(AssignmentModel assignmentModel) {

        Route route = routeService.findRouteEntityByID(assignmentModel.getIdTuyen());
        Driver driver = driverService.findDriverEntityByID(assignmentModel.getIdLaiXe());
        Assignment assignment = Assignment
                .builder()
                .driver(driver)
                .route(route)
                .soLuotChay(assignmentModel.getSoLuotChay())
                .build();

        assignments.add(assignment);
        assignment.setId(AUTO_ID);
        AUTO_ID++;
    }
}

