ALTER TABLE `return_people`
ADD COLUMN `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历' AFTER `self_examination_id`,
ADD COLUMN `research_content` varchar(255) NULL COMMENT '主要科研工作内容' AFTER `education`;
ADD COLUMN `getout_time` datetime NULL COMMENT '离岛时间' AFTER `research_content`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for self_exam_file
-- ----------------------------
DROP TABLE IF EXISTS `self_exam_file`;
CREATE TABLE `self_exam_file`  (
                                   `id` int NOT NULL AUTO_INCREMENT COMMENT '单位附件表',
                                   `file_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件id',
                                   `self_examination_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'baseId(废弃)',
                                   `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件路径',
                                   `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
                                   `type` tinyint NULL DEFAULT NULL COMMENT '类型1:基地(废弃)',
                                   `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                   `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
