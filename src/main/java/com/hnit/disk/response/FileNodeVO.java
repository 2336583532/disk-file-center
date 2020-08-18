package com.hnit.disk.response;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: liguangming
 * @Date: 2020/8/14
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileNodeVO implements Serializable {
    private static final long serialVersionUID = 8483663883914634113L;

    private String fileName;

    private String fileSize;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean isFolder;
}
