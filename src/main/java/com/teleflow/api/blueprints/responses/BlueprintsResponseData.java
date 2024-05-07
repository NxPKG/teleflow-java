package com.teleflow.api.blueprints.responses;
import com.teleflow.api.blueprints.pojos.General;
import com.teleflow.api.blueprints.pojos.Popular;
import lombok.Data;

import java.util.List;

@Data
public class BlueprintsResponseData {
   private List<General> general;
   private Popular popular;
}