/*
 MES数据库补丁
 增加两个新表
 Date: 2020-09-23
*/

-- ----------------------------
-- Table structure for water_flowmeter_dat
-- ----------------------------

IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[water_flowmeter_dat]') AND type IN ('U'))
    DROP TABLE [dbo].[water_flowmeter_dat]
GO

CREATE TABLE [dbo].[water_flowmeter_dat] (
    [id] int  IDENTITY(1,1) NOT NULL,
    [ctime] datetime DEFAULT (getdate()) NOT NULL,
    [device_id] int  NOT NULL,
    [flow_rate] real  NULL,
    [accq_int] int  NULL,
    [accq_float] real  NULL
)
GO

ALTER TABLE [dbo].[water_flowmeter_dat] SET (LOCK_ESCALATION = TABLE)
GO

CREATE NONCLUSTERED INDEX [idx_ctime]
    ON [dbo].[water_flowmeter_dat] (
        [ctime] ASC
    )
GO

ALTER TABLE [dbo].[water_flowmeter_dat] ADD CONSTRAINT [PK__water_fl__A52862C2777804B6] PRIMARY KEY CLUSTERED ([id])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
GO


-- ----------------------------
-- Table structure for steam_flowmeter_dat
-- ----------------------------

IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[steam_flowmeter_dat]') AND type IN ('U'))
    DROP TABLE [dbo].[steam_flowmeter_dat]
GO

CREATE TABLE [dbo].[steam_flowmeter_dat] (
    [id] int  IDENTITY(1,1) NOT NULL,
    [ctime] datetime DEFAULT (getdate()) NOT NULL,
    [device_id] int  NOT NULL,
    [flow_rate1] real  NULL,
    [flow_rate2] real  NULL,
    [flow_total1] real  NULL,
    [flow_total2] real  NULL
)
GO

ALTER TABLE [dbo].[steam_flowmeter_dat] SET (LOCK_ESCALATION = TABLE)
GO

CREATE NONCLUSTERED INDEX [idx_ctime]
    ON [dbo].[steam_flowmeter_dat] (
        [ctime] ASC
    )
GO

ALTER TABLE [dbo].[steam_flowmeter_dat] ADD CONSTRAINT [PK__steam_fl__465F134BA6D95ACC] PRIMARY KEY CLUSTERED ([id])
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
GO
