use oop;

-- 1
select 	country.*, entity.name, entity.description from country, entity
where	entity_id = country_id and
		name = 'Việt Nam';
        
-- 2
select 	title as article 
from 	article
where	publication_date = '2019-09-02';

-- 3
select	country.name as country from entity country, entity organization, fact
where	relationship = 'tham gia' and
		subject_id = country.entity_id and
        object_id = organization.entity_id and
        organization.name = 'WTO';
        
-- 4
select	name as person from entity, person
where	entity_id = person_id and
		job = 'bác sĩ' and
        age < 35;
        
-- 5
select	name as event from entity, event
where	entity_id = event_id and
		year(time) = 2019;
        
-- 6 
select	name as location from entity, location
where	entity_id = location_id and
		country = 'Nhật Bản';
        
-- 7
select 	country1.name as country from fact, entity country1, entity country2
where	subject_id = country1.entity_id and
		relationship = 'căng thẳng với' and
		object_id = country2.entity_id and
        country2.name = 'IRAQ';
        
-- 8
select	country.name as country from fact, entity country, entity agreement
where	subject_id = country.entity_id and
		relationship = 'hủy bỏ' and
        object_id = agreement.entity_id and
        agreement.name = 'hiệp ước Biển Đông COC';
        
-- 9
select	person.name as person from fact, entity person, entity event
where	subject_id = person.entity_id and
		relationship = 'phát biểu tại' and
        object_id = event.entity_id and
        event.name = 'hội nghị Liên Hợp Quốc';
        
-- 10
select	title as article from article
where	title like 'Seagame 30 %' or
		title like '% Seagame 30 %' or
        title like '% Seagame 30';
        
-- 11
select	event.name as event from fact, entity person, entity event
where	month(fact.time) = 8 and
		year(fact.time) = 2019 and
        person.name = 'Nguyễn Phú Trọng' and
        subject_id = person.entity_id and
        object_id = event.entity_id and
        relationship = 'tham gia';
        
-- 12
select	agreement1.name as agreement
from 	fact, entity agreement1, agreement, entity country
where 	subject_id = country.entity_id and
		country.name = 'Việt Nam' and
        relationship = 'ủng hộ' and
        object_id = agreement_id and
        agreement_id = agreement1.entity_id and
        year(contract_date) = 2019;
        
-- 13
select	capital, country1.name as country from entity country1, entity organization, fact, country
where	relationship = 'tham gia' and
		subject_id = country1.entity_id and
        country_id = country1.entity_id and
        object_id = organization.entity_id and
        organization.name = 'WTO' and
        year(time) = 2019;

-- 14
select 	singer.name as singer
from	entity singer, fact, entity event, person
where	job = 'ca sĩ' and
		subject_id = singer.entity_id and
        person_id = singer.entity_id and
        relationship = 'tham gia' and
        object_id = event.entity_id and
        event.name = 'Đông ấm' and
        year(time) = 2019;
        
-- 15
select	event.name as event
from	fact, entity event, entity location
where	subject_id = event.entity_id and
		relationship = 'diễn ra tại' and
		object_id = location.entity_id and
        location.name = 'Sài Gòn' and
        year(time) = 1975;
        
-- 16
select	organization.*, org.name, org.description
from	entity org, organization, country, entity ctry
where	org.entity_id = org_id and
		headquarter = capital and
        ctry.entity_id = country_id and
        ctry.name = 'Hoa Kỳ';
        
-- 17
select	per.name
from	fact, entity per, person, entity evn
where	subject_id = per.entity_id and
		person_id = per.entity_id and
        age < 20 and
		relationship = 'tham gia' and
        object_id = evn.entity_id and
        evn.name = 'Chạy vì môi trường';
  
-- 18
select	count(distinct ctry.name) as number_of_country
from	fact, entity ctry, entity agr
where 	subject_id = ctry.entity_id and 
		relationship = 'hủy bỏ' and
        object_id = agr.entity_id and
        agr.name = 'Xô - Đức';
        
-- 19
select	count(distinct ctry1.name) as number_of_country
from	fact, entity ctry1, entity ctry2
where 	subject_id = ctry1.entity_id and 
		relationship = 'đàm phán' and
        object_id = ctry2.entity_id and
        ctry2.name = 'Việt Nam' and
        year(time) = 2018;
        
-- 20
select	count(distinct player.name) as number_of_u22_player
from	fact, entity player, person, entity event
where	subject_id = player.entity_id and
		person_id = player.entity_id and
        job = 'cầu thủ' and
        age < 22 and
        relationship = 'tham dự' and
        object_id = event.entity_id and
        event.name = 'Seagame 30';