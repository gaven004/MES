/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : SQL Server
 Source Server Version : 14001000
 Source Host           : localhost:1433
 Source Catalog        : MES
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14001000
 File Encoding         : 65001

 Date: 11/09/2019 22:30:08
*/


-- ----------------------------
-- Table structure for flowmeter_dat
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[flowmeter_dat]') AND type IN ('U'))
	DROP TABLE [dbo].[flowmeter_dat]
GO

CREATE TABLE [dbo].[flowmeter_dat] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [ctime] datetime  DEFAULT GETDATE() NOT NULL,
  [device_id] int  NOT NULL,
  [q] real  NULL,
  [v] real  NULL,
  [q_pct] real  NULL,
  [ratio] real  NULL,
  [accq_pos] float(53)  NULL,
  [accq_neg] float(53)  NULL,
  [q_unit] int  NULL,
  [accq_unit] int  NULL
)
GO

ALTER TABLE [dbo].[flowmeter_dat] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'时间戳',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'ctime'
GO

EXEC sp_addextendedproperty
'MS_Description', N'设备id',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'device_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'瞬时流量',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'q'
GO

EXEC sp_addextendedproperty
'MS_Description', N'瞬时流速',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'v'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流量百分比',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'q_pct'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流体电导比',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'ratio'
GO

EXEC sp_addextendedproperty
'MS_Description', N'正向累积',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'accq_pos'
GO

EXEC sp_addextendedproperty
'MS_Description', N'反向累积',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'accq_neg'
GO

EXEC sp_addextendedproperty
'MS_Description', N'瞬时流量单位',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'q_unit'
GO

EXEC sp_addextendedproperty
'MS_Description', N'累积总量单位',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat',
'COLUMN', N'accq_unit'
GO

EXEC sp_addextendedproperty
'MS_Description', N'流量计数据',
'SCHEMA', N'dbo',
'TABLE', N'flowmeter_dat'
GO


-- ----------------------------
-- Records of flowmeter_dat
-- ----------------------------
SET IDENTITY_INSERT [dbo].[flowmeter_dat] ON
GO

INSERT INTO [dbo].[flowmeter_dat] ([id], [ctime], [device_id], [q], [v], [q_pct], [ratio], [accq_pos], [accq_neg], [q_unit], [accq_unit]) VALUES (N'1', N'2019-09-11 21:21:46.000', N'1', N'100', N'6', N'35', NULL, N'100', N'0', N'0', N'0')
GO

INSERT INTO [dbo].[flowmeter_dat] ([id], [ctime], [device_id], [q], [v], [q_pct], [ratio], [accq_pos], [accq_neg], [q_unit], [accq_unit]) VALUES (N'3', N'2019-09-11 21:23:38.000', N'1', N'110', N'6.2', N'40', NULL, N'210', N'0', N'0', N'0')
GO

INSERT INTO [dbo].[flowmeter_dat] ([id], [ctime], [device_id], [q], [v], [q_pct], [ratio], [accq_pos], [accq_neg], [q_unit], [accq_unit]) VALUES (N'4', N'2019-09-11 21:24:43.000', N'1', N'105', N'6.1', N'38', NULL, N'300', N'0', N'0', N'0')
GO

SET IDENTITY_INSERT [dbo].[flowmeter_dat] OFF
GO


-- ----------------------------
-- Table structure for powermeter_dat
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[powermeter_dat]') AND type IN ('U'))
	DROP TABLE [dbo].[powermeter_dat]
GO

CREATE TABLE [dbo].[powermeter_dat] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [ctime] datetime  DEFAULT GETDATE() NOT NULL,
  [device_id] int  NOT NULL,
  [vln_a] real  NULL,
  [vln_b] real  NULL,
  [vln_c] real  NULL,
  [vln_avg] real  NULL,
  [vll_ab] real  NULL,
  [vll_bc] real  NULL,
  [vll_ca] real  NULL,
  [vll_avg] real  NULL,
  [i_a] real  NULL,
  [i_b] real  NULL,
  [i_c] real  NULL,
  [i_avg] real  NULL,
  [frequency] real  NULL,
  [kw_tot] real  NULL,
  [kvar_tot] real  NULL,
  [kva_tot] real  NULL,
  [pf] real  NULL,
  [kwh] real  NULL,
  [kvarh] real  NULL,
  [kvah] real  NULL
)
GO

ALTER TABLE [dbo].[powermeter_dat] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'时间戳',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'ctime'
GO

EXEC sp_addextendedproperty
'MS_Description', N'设备id',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'device_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'A相电压',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'vln_a'
GO

EXEC sp_addextendedproperty
'MS_Description', N'B相电压',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'vln_b'
GO

EXEC sp_addextendedproperty
'MS_Description', N'C相电压',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'vln_c'
GO

EXEC sp_addextendedproperty
'MS_Description', N'相电压平均值',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'vln_avg'
GO

EXEC sp_addextendedproperty
'MS_Description', N'AB线电压',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'vll_ab'
GO

EXEC sp_addextendedproperty
'MS_Description', N'BC线电压',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'vll_bc'
GO

EXEC sp_addextendedproperty
'MS_Description', N'CA线电压',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'vll_ca'
GO

EXEC sp_addextendedproperty
'MS_Description', N'线电压平均值',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'vll_avg'
GO

EXEC sp_addextendedproperty
'MS_Description', N'A相电流',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'i_a'
GO

EXEC sp_addextendedproperty
'MS_Description', N'B相电流',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'i_b'
GO

EXEC sp_addextendedproperty
'MS_Description', N'C相电流',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'i_c'
GO

EXEC sp_addextendedproperty
'MS_Description', N'相电流平均值',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'i_avg'
GO

EXEC sp_addextendedproperty
'MS_Description', N'频率',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'frequency'
GO

EXEC sp_addextendedproperty
'MS_Description', N'总有功功率',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'kw_tot'
GO

EXEC sp_addextendedproperty
'MS_Description', N'总无功功率',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'kvar_tot'
GO

EXEC sp_addextendedproperty
'MS_Description', N'总视在功率',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'kva_tot'
GO

EXEC sp_addextendedproperty
'MS_Description', N'功率因数',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'pf'
GO

EXEC sp_addextendedproperty
'MS_Description', N'有功电能',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'kwh'
GO

EXEC sp_addextendedproperty
'MS_Description', N'无功电能',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'kvarh'
GO

EXEC sp_addextendedproperty
'MS_Description', N'视在电能',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat',
'COLUMN', N'kvah'
GO

EXEC sp_addextendedproperty
'MS_Description', N'电表数据',
'SCHEMA', N'dbo',
'TABLE', N'powermeter_dat'
GO


-- ----------------------------
-- Records of powermeter_dat
-- ----------------------------
SET IDENTITY_INSERT [dbo].[powermeter_dat] ON
GO

INSERT INTO [dbo].[powermeter_dat] ([id], [ctime], [device_id], [vln_a], [vln_b], [vln_c], [vln_avg], [vll_ab], [vll_bc], [vll_ca], [vll_avg], [i_a], [i_b], [i_c], [i_avg], [frequency], [kw_tot], [kvar_tot], [kva_tot], [pf], [kwh], [kvarh], [kvah]) VALUES (N'1', N'2019-09-11 21:42:09.000', N'1', N'220', N'219', N'218', N'219', N'380', N'381', N'382', N'381', N'30', N'35', N'40', N'35', N'50', N'1355', N'125', N'532', N'0.96', N'135.5', N'12.5', N'53.2')
GO

SET IDENTITY_INSERT [dbo].[powermeter_dat] OFF
GO


-- ----------------------------
-- Primary Key structure for table flowmeter_dat
-- ----------------------------
ALTER TABLE [dbo].[flowmeter_dat] ADD CONSTRAINT [PK__flowmete__3213E83FEFCB60C8] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table powermeter_dat
-- ----------------------------
ALTER TABLE [dbo].[powermeter_dat] ADD CONSTRAINT [PK__powermet__3213E83FBE557AF2] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

