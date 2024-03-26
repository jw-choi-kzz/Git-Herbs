import styled from 'styled-components';

const BadgeImage = styled.img`
  width: auto; // Adjust width as necessary
  height: 60px; // Adjust height to fit the design
  // border-radius: 10px; // Optional: Rounded corners for the image
  filter: drop-shadow(0 4px 8px rgba(0, 0, 0, 0.2)); // Shadow effect following the shape of the image
`;

const BedgeListItem = () => {
  return (
    <>
      <BadgeImage src='/bedge.png' alt='Badge' />
      <BadgeImage src='/bedge.png' alt='Badge' />
      <BadgeImage src='/bedge.png' alt='Badge' />
      <BadgeImage src='/bedge.png' alt='Badge' />
      <BadgeImage src='/bedge.png' alt='Badge' />
      <BadgeImage src='/bedge.png' alt='Badge' />
    </>
  );
};

export default BedgeListItem;