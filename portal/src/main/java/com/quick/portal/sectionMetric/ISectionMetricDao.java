package com.quick.portal.sectionMetric;

import java.util.List;
import java.util.Map;

import com.quick.core.base.ISysBaseDao;

public interface ISectionMetricDao<SectionMetricDO> extends ISysBaseDao<SectionMetricDO>  {
	 List<Map<String,Object>> getId(int section_id);
}
