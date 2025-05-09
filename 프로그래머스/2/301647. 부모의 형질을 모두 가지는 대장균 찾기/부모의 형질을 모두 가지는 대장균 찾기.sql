select 
    child.id as ID,
    child.genotype as GENOTYPE,
    parent.genotype as PARENT_GENOTYPE
from ecoli_data as child
join ecoli_data as parent
on child.parent_id = parent.id
where (child.genotype & parent.genotype) = parent.genotype
order by ID;