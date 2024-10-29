package by.zemich.newsms.core.service;

import by.zemich.newsms.core.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-ms")
public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/api/v1/user")
    UserDTO getUserByUsername(@PathVariable String username);

}
