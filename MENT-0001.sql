

####################################### 
###################### Specialities 
####################################### 



####################################### 
###################### Provider Location 
####################################### 



####################################### 
###################### Provider Participation 
####################################### 
REPLACE INTO `provider_participations` (`id`, `provider_network_id`, `accepting_new_patients`, `primary_phone`, `provider_id`, `provider_role`, `facility`, `expired_at`, `updated_at`, `provider_location_id`) VALUES 
(114100195,9482,1,'(510) 652-7000',15294180,NULL,NULL,NULL,NULL,1797072) 
,(114100196,9482,1,NULL,1998261,NULL,NULL,NULL,NULL,4986125) ;

####################################### 
###################### Providers 
####################################### 



####################################### 
###################### Providers Specialities  
####################################### 
REPLACE INTO `provider_specialties` (`id`, `provider_id`, `specialty_id`, `created_at`, `updated_at`) VALUES 
(8710421,15294180,1331, now() , NULL) ,
(8710422,1998261,1340, now() , NULL) ,
(8710423,1998261,1342, now() , NULL) ;


####################################### 
###################### Provider Affiliation 
####################################### 
REPLACE INTO `provider_affiliations` (`id`,`provider_id`,`parent_provider_id`,`affiliation_type`,`source`,`created_at`,`updated_at` ) VALUES 
( 1,1998261,15294180,'NA','SKA', now() , NULL ) ;


####################################### 
###################### Provider Metrics  
####################################### 
REPLACE INTO `provider_metrics` (`id`, `provider_id`, `metric_name`, `decimal_value`, `decimal_value_avg`, `string_value`, `sample_size`, `data_provided_by`, `data_date`, `created_at`, `updated_at`) VALUES(1,15294180,'SATISFACTION_RECOMMEND_HOSPITAL',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(2,15294180,'SATISFACTION_COMMUNICATION_DOCTORS',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(3,15294180,'SATISFACTION_COMMUNICATION_NURSES',3.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(4,15294180,'SATISFACTION_DISCHARGE_INSTRUCTIONS',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(5,15294180,'SATISFACTION_PAIN_CONTROLLED',3.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(6,15294180,'SATISFACTION_MEDICINES_EXPLAINED',3.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(7,15294180,'SATISFACTION_ROOM_CLEAN',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(8,15294180,'SATISFACTION_ROOM_QUIET',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(9,15294180,'SATISFACTION_HELP_ASAP',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(10,1998261,'SATISFACTION_RECOMMEND_HOSPITAL',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(11,1998261,'SATISFACTION_COMMUNICATION_DOCTORS',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(12,1998261,'SATISFACTION_COMMUNICATION_NURSES',3.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(13,1998261,'SATISFACTION_DISCHARGE_INSTRUCTIONS',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(14,1998261,'SATISFACTION_PAIN_CONTROLLED',3.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(15,1998261,'SATISFACTION_MEDICINES_EXPLAINED',3.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(16,1998261,'SATISFACTION_ROOM_CLEAN',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(17,1998261,'SATISFACTION_ROOM_QUIET',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL),
(18,1998261,'SATISFACTION_HELP_ASAP',5.0,3.0,'3','300+','CMS','January 2016', now() , NULL);

Delete from provider_metrics where id in (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)




####################################### 
###################### Pricing 
####################################### 
use bucket_1;
db.priceables.remove({"pm":6907});
db.priceables.insert([
{"_id" : NumberLong("95011546162"), "pm": NumberInt(6907), "pp": NumberInt(114100191), "pn": NumberInt(9482), "loc": [37.81907, 37.81907], "amt": NumberInt(2168.0), "pl": NumberInt(615.0), "pu": NumberInt(1553.0), "spe": false, "pi": NumberInt(0), "pt": "rg"},
{"_id" : NumberLong("95011546163"), "pm": NumberInt(6907), "pp": NumberInt(114100194), "pn": NumberInt(9482), "loc": [37.360424, 37.360424], "amt": NumberInt(2168.0), "pl": NumberInt(615.0), "pu": NumberInt(1553.0), "spe": false, "pi": NumberInt(0), "pt": "rg"}
]);