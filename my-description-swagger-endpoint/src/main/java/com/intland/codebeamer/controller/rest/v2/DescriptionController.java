package com.intland.codebeamer.controller.rest.v2;

import com.intland.codebeamer.controller.rest.v2.exception.*;
import com.intland.codebeamer.manager.TrackerItemManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@OpenAPIDefinition
@RestController
@RequestMapping(AbstractRestController.API_URI_V3)
public class DescriptionController extends AbstractRestController
{
    @Autowired
    TrackerItemManager trackerItemManager;

    public static final Logger logger = Logger.getLogger(DescriptionController.class);

    @Operation(summary = "Get description of an item", tags = "Example")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Description provided"),
            @ApiResponse(responseCode = "403", description = "Authentication is required"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "429", description = "Too many requests", content = @Content(schema = @Schema(implementation = TooManyRequestsException.class)))
    })
    @RequestMapping(value = "{id}/description", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getDescription(@PathVariable("id") Integer id) {
        logger.info("Get description of an item");
        return trackerItemManager.findById(id).getDescription();
    }

    @Operation(summary = "Update description of an item", tags = "Example")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Description updated"),
            @ApiResponse(responseCode = "403", description = "Authentication is required"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "429", description = "Too many requests", content = @Content(schema = @Schema(implementation = TooManyRequestsException.class)))
    })
    @RequestMapping(value = "{id}/description", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateDescription(@PathVariable("id") Integer id,
                                                  @RequestBody String description) {
        logger.info("Update description of an item");
        trackerItemManager.findById(id).setDescription(description);
        return trackerItemManager.findById(id).getDescription();
    }
}
