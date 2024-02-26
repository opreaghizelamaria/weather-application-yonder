package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.LinkedHashSet;
import java.util.Set;
import org.openapitools.model.City;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Result
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-26T11:46:05.403406+02:00[Europe/Bucharest]")
public class Result {

  @Valid
  private Set<@Valid City> result = new LinkedHashSet<>();

  public Result() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Result(Set<@Valid City> result) {
    this.result = result;
  }

  public Result result(Set<@Valid City> result) {
    this.result = result;
    return this;
  }

  public Result addResultItem(City resultItem) {
    if (this.result == null) {
      this.result = new LinkedHashSet<>();
    }
    this.result.add(resultItem);
    return this;
  }

  /**
   * Get result
   * @return result
  */
  @NotNull @Valid 
  @Schema(name = "result", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("result")
  public Set<@Valid City> getResult() {
    return result;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setResult(Set<@Valid City> result) {
    this.result = result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Result result = (Result) o;
    return Objects.equals(this.result, result.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Result {\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

