#update the jars
scp -r ../jars/ moons.cs.unm.edu:/nfs/student/${USER:0:1}/$USER/
scp -r ../res/AuctionHouse/* moons.cs.unm.edu:/nfs/student/${USER:0:1}/$USER/AuctionHouse/

echo "Warning this job will kill all jobs running on the moons systems!"
echo "Press enter to continue"
read meep

#@TODO make a for loop to clean up
ssh -o StrictHostKeyChecking=no ganymede.cs.unm.edu 'pkill -U $USER'
ssh -o StrictHostKeyChecking=no chawla.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no phoebe.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no sycorax.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no brown.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no hemera.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no deimos.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no ramon.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no prospero.cs.unm.edu 'pkill -U $USER'  
ssh -o StrictHostKeyChecking=no phobos.cs.unm.edu 'pkill -U $USER'  
ssh -o StrictHostKeyChecking=no callisto.cs.unm.edu 'pkill -U $USER'
ssh -o StrictHostKeyChecking=no leda.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no titan.cs.unm.edu 'pkill -U $USER' 
ssh -o StrictHostKeyChecking=no europa.cs.unm.edu 'pkill -U $USER'
