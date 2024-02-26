package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * City
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-26T11:52:35.368860+02:00[Europe/Bucharest]")
public class City {

  private String name;

  private String temperature;

  private String wind;

  public City() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public City(String name, String temperature, String wind) {
    this.name = name;
    this.temperature = temperature;
    this.wind = wind;
  }

  public City name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public City temperature(String temperature) {
    this.temperature = temperature;
    return this;
  }

  /**
   * Get temperature
   * @return temperature
  */
  @NotNull 
  @Schema(name = "temperature", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("temperature")
  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public City wind(String wind) {
    this.wind = wind;
    return this;
  }

  /**
   * Get wind
   * @return wind
  */
  @NotNull 
  @Schema(name = "wind", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("wind")
  public String getWind() {
    return wind;
  }

  public void setWind(String wind) {
    this.wind = wind;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    City city = (City) o;
    return Objects.equals(this.name, city.name) &&
        Objects.equals(this.temperature, city.temperature) &&
        Objects.equals(this.wind, city.wind);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, temperature, wind);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class City {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    wind: ").append(toIndentedString(wind)).append("\n");
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

