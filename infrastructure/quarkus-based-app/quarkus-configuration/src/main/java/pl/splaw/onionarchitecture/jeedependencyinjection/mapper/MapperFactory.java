package pl.splaw.onionarchitecture.jeedependencyinjection.mapper;

import pl.splaw.onionarchitecture.databaserepository.mapper.DomainToEntityMapper;
import pl.splaw.onionarchitecture.quarkusrestservices.mapper.DomainToResponseMapper;
import pl.splaw.onionarchitecture.quarkusrestservices.mapper.RequestsToDomainMapper;

/**
 *
 * @ApplicationScoped deactivated because all components to inject are allready = @ApplicationScoped
 *
 * @author Bartek <https://github.com/splaw88>
 */
//@ApplicationScoped
public class MapperFactory {
  
  // @Produces
  // @ApplicationScoped
  public DomainToEntityMapper domainToEntityMapper() {
    return new DomainToEntityMapper();
  }
  
  // @Produces
  // @ApplicationScoped
  public DomainToResponseMapper domainToResponseMapper() {
    return new DomainToResponseMapper();
  }
  
  // @Produces
  // @ApplicationScoped
  public RequestsToDomainMapper requestsToDomainMapper() {
    return new RequestsToDomainMapper();
  }
  
}
