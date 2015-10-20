package com.netflix.karyon.archaius;

import java.net.InetAddress;

import javax.inject.Singleton;

import com.netflix.archaius.Config;
import com.netflix.archaius.config.MapConfig;
import com.netflix.archaius.guice.ConfigSeeder;
import com.netflix.karyon.ServerContext;

/**
 * Used to seed a configuration layer for ServerContext keys with local server
 * information
 * 
 * @author elandau
 */
@Singleton
public class LocalServerContextConfigSeeder implements ConfigSeeder {

    @Override
    public Config get(Config rootConfig) throws Exception {
        InetAddress IP = InetAddress.getLocalHost();
        return MapConfig.builder()
            // Amazon specific metadata
            .put(ServerContext.DOMAIN,          "")
            .put(ServerContext.HOSTNAME,        "localhost")
            .put(ServerContext.PUBLIC_HOSTNAME, "localhost") // IP.getHostName())
            .put(ServerContext.PUBLIC_IPV4,     IP.getHostAddress())
            .put(ServerContext.LOCAL_HOSTNAME,  IP.getHostName())
            .put(ServerContext.LOCAL_IPV4,      IP.getHostAddress())
            .put(ServerContext.DATACENTER,      "cloud")
            .put(ServerContext.REGION,          "us-west-2")
            .put(ServerContext.ZONE,            "us-west-2a")
            .put(ServerContext.SERVER_ID,       "${" + ServerContext.HOSTNAME + "}")
            .put(ServerContext.AMI,             "ami-dev")
            
            .put("EC2_DOMAIN",              "${" + ServerContext.DOMAIN + "}")
            .put("EC2_HOSTNAME",            "${" + ServerContext.HOSTNAME + "}")
            .put("EC2_PUBLIC_HOSTNAME",     "${" + ServerContext.PUBLIC_HOSTNAME + "}")
            .put("EC2_PUBLIC_IPV4",         "${" + ServerContext.PUBLIC_IPV4 + "}")
            .put("EC2_LOCAL_HOSTNAME",      "${" + ServerContext.LOCAL_HOSTNAME + "}")
            .put("EC2_LOCAL_IPV4",          "${" + ServerContext.LOCAL_IPV4 + "}")
            .put("EC2_AVAILABILITY_ZONE",   "${" + ServerContext.ZONE + "}")
            .put("EC2_INSTANCE_ID",         "${" + ServerContext.SERVER_ID + "}")
            .put("EC2_AMI_ID",              "${" + ServerContext.AMI + "}")
            .put("EC2_REGION",              "${" + ServerContext.REGION + "}")
            
            .build();
    }
}
